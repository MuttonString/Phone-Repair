const token = localStorage.getItem('token');
const role = localStorage.getItem('role');
if (!token || !role) {
    location.href = "login.html";
} else {
    $.post(base_url + "check", { "token": token }, data => {
        if (data.data == "false") {
            location.href = "login.html";
        }
    })
}