// Al cambiar el depósito o la casilla, se filtra sin tener que tocar el botón
const formularioFiltros = document.querySelector('.filtros');

document.getElementById('depositoId').addEventListener('change', function () {
    formularioFiltros.submit();
});

document.getElementById('soloStockBajo').addEventListener('change', function () {
    formularioFiltros.submit();
});