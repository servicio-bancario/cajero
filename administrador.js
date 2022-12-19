const url = "http://localhost:8080/Administrador/"
const url1 = "http://localhost:8080/Administrador/list"

const contenedor = document.querySelector('tbody')

let resultados = ''

const modalAdministrador = new bootstrap.Modal(document.getElementById('modalAdministrador'))
const formAdministrador = document.querySelector('form')
const idAdministrador= document.getElementById('id')
const nombreAdministrador = document.getElementById('nombre')
const claveAdministrador = document.getElementById('clave')

let opcion = ''

btnCrear.addEventListener('click', () => {
    idAdministrador.value = ''
    nombreAdministrador.value = ''
    claveAdministrador.value = ''
    idAdministrador.disabled = false
    modalAdministrador.show()
    opcion = 'crear'
})

const mostrar = (Administrador) => {
    Administrador.forEach(Administrador => {
        resultados += `<tr>
                        <td >${Administrador.id_Administrador}</td>
                        <td >${Administrador.nombre_administrador}</td>
                        <td >${Administrador.clave_administrador}</td>
                        <td class="text-center" width="20%"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                    </tr>`
    })

    contenedor.innerHTML = resultados
}

fetch(url1)
    .then(response => response.json())
    .then(data => mostrar(data))
    .catch(error => console.log(error))

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector))
            handler(e)
    })
}

on(document, 'click', '.btnBorrar', e => {
    const fila = e.target.parentNode.parentNode
    const id = fila.firstElementChild.innerHTML
    console.log(id)

    alertify.confirm("Desea eliminar el Administrador "+id,
        function () {
            fetch(url + id, {
                method: 'DELETE'
            })
                .then(() => location.reload())
        },
        function () {
            alertify.error('Cancel')
        });
})


let idForm = 0
on(document, 'click', '.btnEditar', e => {

    const fila = e.target.parentNode.parentNode
    
    idForm = fila.children[0].innerHTML
    const nombre = fila.children[1].innerHTML
    const clave = fila.children[2].innerHTML
    idAdministrador.value = idForm
    idAdministrador.disabled = true
    nombreAdministrador.value = nombre
    claveAdministrador.value = clave

    opcion = 'editar'
    modalAdministrador.show()
})

formAdministrador.addEventListener('submit', (e) => {
    e.preventDefault()

        if (opcion == 'crear') {
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_administrador: idAdministrador.value,
                    nombre_administrador: nombreAdministrador.value,
                    clave_administrador: claveAdministrador.value
                })
            })
                .then(response => response.json())
                .then(data => {
                    const nuevoAdministrador = []
                    nuevoAdministrador.push(data)
                    mostrar(nuevoAdministrador)
                })
        }
        if (opcion == 'editar') {

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_administrador: idAdministrador.value,
                    nombre_administrador: nombreAdministrador.value,
                    clave_administrador: claveAdministrador.value
                })
            })
                .then(response => location.reload())

        }
        modalAdministrador.hide()
    
})



