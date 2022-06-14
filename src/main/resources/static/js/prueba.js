async function login(){

         let datos = {};
         datos.email = document.getElementById('txtEmail').value;
         datos.password = document.getElementById('txtPassword').value;

        console.log(datos.password)
        console.log(datos.email)

       const request = await fetch('auth/login', {
         method: 'POST',
         headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
         },

         body: JSON.stringify(datos)

       });

       const respuesta = await request.text();
       console.log(respuesta)

       if(respuesta != "Authentication Failed"){
         localStorage.token = respuesta;
         localStorage.email = datos.email;
         window.location.href = "usuarios.html";
       }else{
         alert("Las credenciales son incorrectas. Intente otra vez.");
       }

 }

 function getHeaders(){//Permite reutilizar código
     return {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json',
                      'Authorization': localStorage.token //esto envía el token como header

            }
 }

 async function cargarUsuarios(){

       const request = await fetch('api/usuarios', {
         method: 'GET',
         headers: getHeaders()
       });
       const users = await request.json();

       let listadoHtml = '';

       for(let usuario of users){
         let botonEliminar = '<td><a href="#" onclick="eliminarUsuario('+usuario.id+')"> Eliminar </a></td>';

         let usuarioHtml =
         '<tr><td>'+ usuario.nombre +' ' + usuario.apellido+ '</td>'+
         '<td>'+ usuario.email+'</td>'+
         botonEliminar+
         "</tr>"

         listadoHtml += usuarioHtml;
       }

       console.log(usuarios);

       document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

 }

async function register(){

        let datos = {};
        datos.name = document.getElementById('txtNombre').value;
        datos.lastname = document.getElementById('txtApellido').value;
        datos.email = document.getElementById('txtEmail').value;
        datos.password = document.getElementById('txtPassword').value;

        let repetirPassword = document.getElementById('txtRepetirPassword').value;

        if(repetirPassword != datos.password){
            alert('La contraseña que escribiste es diferente');
            return;
        }

      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      alert("La registración se ha realizaco exitosamente");
      window.location.href = "prueba.html";

}