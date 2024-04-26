const { createApp } = Vue;
createApp({
    data() {
        return {
            task_list: [],
            task: {}
        };
    },
    mounted() {},
    methods: {
        checkTask: function (cus_phone) {
            var that = this;
            $.post(
                base_url + 'TaskServlet',
                { ac: 'check', cus_phone: cus_phone },
                function (data) {
                    console.log(data);
                    if (data.code != 0) {
                        that.task_list = data.data;
                    } else {
                        alert('未查询到结果');
                    }
                }
            );
        }
    }
}).mount('#app');
