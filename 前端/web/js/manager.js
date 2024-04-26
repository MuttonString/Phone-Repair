const { createApp } = Vue;
createApp({
    data() {
        return {
            msg: "",
            cus_view: false,
            service_view: false,
            fittings_view: false,
            acc_view: false,
            factory_view: false,
            fittings_list: [],
            task_list: [],
            task: {
                "ac": "save"
            },
            admin: {
                "ac": "reg"
            },
            acc_list: [],
            role: 0,
            factory_list: [],
            factory: {}
        };
    },
    mounted() {
        this.role = localStorage.getItem("role");
        $("#info").text(`【${localStorage.getItem("admin_name")}（${this.role == 0 ? '管理员' : (this.role == 1 ? '前台接待' : (this.role == 2 ? '维修人员' : '检测人员'))}）】`);
        switch (this.role) {
            case "0": // 管理员
            case "1": // 前台接待
                this.show_panel(0); // 初始显示前台接待页面
                break;
            case "2": // 维修人员
            case "3": // 检测人员
                this.show_panel(1); // 初始显示维修管理页面
                break;
        }
        window.addEventListener("storage", () => { // 防止存储在localStorage里的role属性被浏览器的开发人员工具更改
            localStorage.setItem("role", this.role);
        });

    },
    methods: {
        logout: function () {
            const result = confirm("确定退出？")
            if (result) {
                localStorage.removeItem("admin_name");
                localStorage.removeItem("token");
                localStorage.removeItem("role");
                location.href = "login.html";
            }
        },
        show_panel: function (panel_id) {
            switch (panel_id) {
                case 0:
                    this.cus_view = true;
                    this.service_view = false;
                    this.fittings_view = false;
                    this.acc_view = false;
                    this.factory_view = false;
                    this.loadTaskData();
                    break;
                case 1:
                    this.cus_view = false;
                    this.service_view = true;
                    this.fittings_view = false;
                    this.acc_view = false;
                    this.factory_view = false;
                    this.loadTaskData();
                    break;
                case 2:
                    this.cus_view = false;
                    this.service_view = false;
                    this.fittings_view = true;
                    this.acc_view = false;
                    this.factory_view = false;
                    this.loadFittingsData();
                    break;
                case 3:
                    this.cus_view = false;
                    this.service_view = false;
                    this.fittings_view = false;
                    this.acc_view = true;
                    this.factory_view = false;
                    this.loadAccData();
                    break;
                case 4:
                    this.cus_view = false;
                    this.service_view = false;
                    this.fittings_view = false;
                    this.acc_view = false;
                    this.factory_view = true;
                    this.loadFactoryData();
                    break;
            }
        },
        loadFittingsData: function () {
            $.post(base_url + "FittingsServlet", { "ac": "list" }, data => {
                if (data.code != 0) {
                    this.fittings_list = data.data;
                }
            });
        },
        loadTaskData: function () {
            $.post(base_url + "TaskServlet", { "ac": "list" }, data => {
                if (data.code != 0) {
                    this.task_list = data.data;
                }
            });
        },
        delTask: function (task_id, task_no) {
            const result = confirm(`确认删除任务编号【${task_no}】？`);
            if (result) {
                $.post(base_url + "TaskServlet", { "ac": "del", "task_id": task_id }, data => {
                    if (data.code == 1) {
                        alert("删除成功")
                        this.loadTaskData();
                    } else {
                        alert("删除失败");
                    }
                });
            }
        },
        saveTask: function () {
            if (!this.task.cus_name) {
                alert("客户姓名不能为空");
                return;
            }
            if (!this.task.cus_phone) {
                alert("客户电话不能为空");
                return;
            }
            if (!/^[0-9]{11}$/.test(this.task.cus_phone)) {
                alert("客户电话为11位数字");
                return;
            }
            if (!this.task.service_item) {
                alert("请选择维修项目");
                return;
            }
            const that = this;
            $.post(base_url + "TaskServlet", this.task, function (data) {
                if (data.code == 1) {
                    alert("保存成功");
                    that.task = { "ac": "save" };
                    that.loadTaskData();
                } else {
                    alert("保存失败");
                }
            });
        },
        updateTask: function (task_id, task_state, service_item) {
            $.post(base_url + "TaskServlet", { "ac": "update", "task_id": task_id, "task_state": task_state, "service_item": service_item }, data => {
                if (data.code == 1) {
                    alert("状态更新成功");
                    this.loadTaskData();
                } else {
                    alert("状态更新失败");
                }
            });
        },
        addQty: function (fit_id) {
            const fit_qty = Number.parseInt(prompt("要增加的库存数量："));
            if (!fit_qty) {
                alert("请输入非零整数");
                return;
            }
            $.post(base_url + "FittingsServlet", { "ac": "update", "fit_id": fit_id, "fit_qty": fit_qty }, data => {
                if (data.code == 1) {
                    alert("更新成功");
                    this.loadFittingsData();
                } else {
                    alert("更新失败");
                }
            });
        },
        loadAccData: function () {
            $.post(base_url + "AccountServlet", { "ac": "list" }, data => {
                if (data.code != 0) {
                    this.acc_list = data.data;
                }
            });
        },
        updateAcc: function (aid, admin_state, admin_pwd, admin_name, changePwd = false) {
            if (admin_name === localStorage.getItem("admin_name")) {
                alert("不能更改自己的账户状态");
                return;
            }
            if (changePwd) {
                const pwd = prompt(`输入【${admin_name}】的新密码`);
                const pwd_ = prompt("再次输入密码");
                if (!pwd) {
                    alert("密码不能为空");
                    return;
                }
                if (pwd != pwd_) {
                    alert("两次输入的密码不一致");
                    return;
                }
                admin_pwd = pwd;
            }
            $.post(base_url + "AccountServlet", { "ac": "update", "aid": aid, "admin_state": admin_state, "admin_pwd": admin_pwd }, data => {
                if (data.code != 0) {
                    alert("操作成功");
                    this.loadAccData();
                } else {
                    alert("操作失败");
                }
            });
        },
        regist: function () {
            if (!this.admin.admin_name) {
                alert("用户名不能为空");
                return;
            }
            if (!this.admin.admin_pwd) {
                alert("密码不能为空");
                return;
            }
            if (this.admin.admin_pwd != this.admin.admin_pwd_) {
                alert("两次输入的密码不一致");
                return;
            }
            if (!this.admin.admin_role) {
                alert("请选择用户角色");
                return;
            }
            const that = this;
            console.log(this.admin)
            $.post(base_url + "AccountServlet", this.admin, function (data) {
                if (data.code == 1) {
                    alert("注册成功");
                    that.admin = { "ac": "reg" };
                    that.loadAccData();
                } else {
                    alert("注册失败，请检查是否已存在相同的用户名");
                }
            });
        },
        loadFactoryData: function () {
            var that = this;
            $.post(base_url + "FactoryServlet", { "ac": "list" }, function (data) {
                if (data.code != 0) {
                    //将数据给vue中data中的list
                    that.factory_list = data.data;
                    console.log(that.factory_list)
                }
            });
        },
        saveFac: function () {
            console.log(this.factory.fac_name);
            if (!this.factory.fac_name) {
                alert("供应商名不能为空");
                return;
            }
            console.log(this.factory.fac_phone);
            if (!this.factory.fac_phone) {
                alert("联系电话不能为空");
                return;
            }
            console.log(this.factory.fac_item);
            if (!this.factory.fac_item) {
                alert("请选择提供的配件");
                return;
            }
            //收到数据
            var that = this;
            //给task添加一个属性名 值为save
            this.factory.ac = "save";
            //json
            $.post(base_url + "FactoryServlet", this.factory, function (data) {
                if (data.code == 1) {
                    alert("添加成功");
                    that.loadFactoryData();
                } else {
                    alert("添加失败");
                }
            });
        },
        updateFac: function (fac_id, fac_state) {
            $.post(base_url + "FactoryServlet", { "ac": "update", "fac_id": fac_id, "fac_state": fac_state }, data => {
                if (data.code != 0) {
                    alert("操作成功");
                    this.loadFactoryData();
                } else {
                    alert("操作失败");
                }
            });
        }
    }
}).mount("#app");