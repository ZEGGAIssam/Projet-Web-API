  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyAs2LPCnRB3-RkVka08B9O0fZ4V31LBn7I",
    authDomain: "apiweb-b91b2.firebaseapp.com",
    databaseURL: "https://apiweb-b91b2.firebaseio.com",
    projectId: "apiweb-b91b2",
    storageBucket: "apiweb-b91b2.appspot.com",
    messagingSenderId: "197936506089",
    appId: "1:197936506089:web:43c8303681c17e22f2fc78",
    measurementId: "G-8JJY0GCMZK"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  //firebase.analytics();
  
  const auth = firebase.auth();
  
  function signUp(){
	var email = document.getElementById("email");
	var password = document.getElementById("password");
	
	const promise = auth.createUserWithEmailAndPassword(email.value, password.value);
	promise.catch(e => alert(e.message));
	
	alert("Signed Up");
  }
  
  function signIn(){
	var email = document.getElementById("email");
	var password = document.getElementById("password");
	
	const promise = auth.signInWithEmailAndPassword(email.value, password.value);
	promise.catch(e => alert(e.message));
	
	alert("Signed In" + email.value);
  }  
  
  function signOut(){
	  
	auth.signOut(); 
	alert("Signed Out");
  }    
  
  auth.onAuthStateChanged(function(user){
		if(user){
			var email = user.email;
			alert("Signed In" + email);
		}
		else{
			alert("No Active User");
		}
	 
  });