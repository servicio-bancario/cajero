const url = "http://localhost:8080/cuenta/"
const url1 = "http://localhost:8080/cuenta/list"

const contenedor = document.querySelector('tbody')

let resultados = ''

const modalCuenta = new bootstrap.Modal(document.getElementById('modalCuenta'))
const formCuenta = document.querySelector('form')
const idCuenta = document.getElementById('id')
const nombreCuenta = document.getElementById('nombre')
const claveCuenta = document.getElementById('clave')

let opcion = ''

btnCrear.addEventListener('click', () => {
    idCuenta.value = ''
    nombreCuenta.value = ''
    claveCuenta.value = ''
    idCuenta.disabled = false
    modalCuenta.show()
    opcion = 'crear'
})

const mostrar = (Cuenta) => {
    Cuenta.forEach(Cuenta => {
        resultados += `<tr>
                        <td >${Cuenta.id_cuenta}</td>
                        <td >${Cuenta.nombre_cuenta}</td>
                        <td >${Cuenta.clave_cuenta}</td>
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

    alertify.confirm("Desea eliminar el Cliente "+id,
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
    idCuenta.value = idForm
    idCuenta.disabled = true
    nombreCliente.value = nombre
    claveCuenta.value = clave

    opcion = 'editar'
    modalCuenta.show()
})

formClientes.addEventListener('submit', (e) => {
    e.preventDefault()

        if (opcion == 'crear') {
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_cuenta: idCuenta.value,
                    nombre_cuenta: nombreCuenta.value,
                    clave_cuenta: claveCuenta.value
                })
            })
                .then(response => response.json())
                .then(data => {
                    const nuevaCuenta = []
                    nuevaCuenta.push(data)
                    mostrar(nuevaCuenta)
                })
        }
        if (opcion == 'editar') {

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_cuenta: idCuenta.value,
                    nombre_cuenta: nombreCuenta.value,
                    clave_cuenta: claveCuenta.value
                })
            })
                .then(response => location.reload())

        }
        modalCuenta.hide()
    
})



