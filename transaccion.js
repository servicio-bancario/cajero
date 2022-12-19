const url = "http://localhost:8080/transaccion/"
const url1 = "http://localhost:8080/transaccion/list"

const contenedor = document.querySelector('tbody')

let resultados = ''

const modalTransaccion = new bootstrap.Modal(document.getElementById('modalTransaccion'))
const formClientes = document.querySelector('form')
const idTransaccion = document.getElementById('id')
const nombreTransaccion = document.getElementById('nombre')
const claveTransaccion = document.getElementById('clave')

let opcion = ''

btnCrear.addEventListener('click', () => {
    idTransaccion.value = ''
    nombreTransaccion.value = ''
    claveTransaccion.value = ''
    idTransaccion.disabled = false
    modalTransaccion.show()
    opcion = 'crear'
})

const mostrar = (transaccion) => {
    transaccion.forEach(transaccion => {
        resultados += `<tr>
                        <td >${transaccion.id_transaccion}</td>
                        <td >${transaccion.nombre_transaccion}</td>
                        <td >${transaccion.clave_transaccion}</td>
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

    alertify.confirm("Desea eliminar el Transaccion "+id,
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
    idTransaccion.value = idForm
    idTransaccion.disabled = true
    nombreTransaccion.value = nombre
    claveTransaccion.value = clave

    opcion = 'editar'
    modalTransaccion.show()
})

formTransaccion.addEventListener('submit', (e) => {
    e.preventDefault()

        if (opcion == 'crear') {
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_transaccion: idTransaccion.value,
                    nombre_transaccion: nombreTransaccion.value,
                    clave_transaccion: claveTransaccion.value
                })
            })
                .then(response => response.json())
                .then(data => {
                    const nuevatransaccion = []
                    nuevaTransaccion.push(data)
                    mostrar(nuevaTransaccion)
                })
        }
        if (opcion == 'editar') {

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_transaccion: idTransaccion.value,
                    nombre_transaccion: nombreTransaccion.value,
                    clave_transaccion: claveTransaccion.value
                })
            })
                .then(response => location.reload())

        }
        modalTransaccion.hide()
    
})



