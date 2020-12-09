
window.addEventListener("load", function () {
    document.getElementById("registerForm").addEventListener("submit", function (event) {
      event.preventDefault();
      sendData(POST, domaine + "register", new FormData(document.getElementById("registerForm")));
    });
});