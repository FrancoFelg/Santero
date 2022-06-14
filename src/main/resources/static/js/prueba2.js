$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
});


async function cargarUsuarios(){

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()

      });
      const usuarios = await request.json();

      let listadoHtml = '';

      for(let usuario of usuarios){
        let botonEliminar = '<td><a href="#" onclick="eliminarUsuario('+usuario.id+')"> Eliminar </a></td>';

        let usuarioHtml =
        '<tr><td>'+ usuario.name +' ' + usuario.lastname+ '</td>'+
        '<td>'+ usuario.email+'</td>'+
        botonEliminar+
        "</tr>"


        listadoHtml += usuarioHtml;
      }

      console.log(usuarios);

      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

function getHeaders(){//Permite reutilizar código
    return {
                     'Accept': 'application/json',
                     'Content-Type': 'application/json',
                     'Authorization': localStorage.token //esto envía el token como header

                   }
}