const POST = "POST";
const domaine ="/";
function sendData(method, url, data) {
    var XHR = new XMLHttpRequest();
    // Définissez ce qui se passe si la soumission s'est opérée avec succès
    XHR.addEventListener("load", function(event) {
      alert("let's go");
    });

    // Definissez ce qui se passe en cas d'erreur
    XHR.addEventListener("error", function(event) {
      alert('Oups! Quelque chose s\'est mal passé.');
    });

    // Configurez la requête
    XHR.open(method, url);
    XHR.withCredentials = true;
    XHR.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    XHR.send(data);
  };