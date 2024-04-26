const { createApp } = Vue;
createApp({
    data() {
        return {
            task_list: []
        }
    },
    mounted() {
        this.loadTaskData();
        setInterval(this.loadTaskData, 1000);
    },
    methods: {
        loadTaskData: function () {
            $.post(base_url + "TaskServlet", { "ac": "list" }, data => {
                if (data.code != 0) {
                    this.task_list = data.data;
                }
            });
        }
    }
}).mount("#app");