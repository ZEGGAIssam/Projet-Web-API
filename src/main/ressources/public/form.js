window.addEventListener("load", function () {
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();
        sendData(POST, domaine + "login", new FormData(document.getElementById("loginForm")));
    });
});