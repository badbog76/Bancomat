function listProductsUL(productsjson) {

    var list = document.getElementById('listOfProducts').getElementsByTagName('ul')[0];
    var p = productsjson[1];
    var el= '<li>Soldul dumneavoastra curent este de :'+p.sold+' lei</li>';

    list.innerHTML = el;
}
function listOfProducts() {
    $.ajax({
        url: 'productsurl?action=list'
    }).done(function (response) {
        listProductsUL(response.productsjson);
    });
}
function listProductsUL1(productsjson) {

    var list = document.getElementById('listOfProducts1').getElementsByTagName('tr')[0];
    var listHtml = '';

    for (var i = productsjson.length -1; i >= 0; i--) {
        var p = productsjson[i];
        if(p.bani!=0)
        {
            if(p.tip == 0) {
                var el = '<li style="list-style-type: none">' + p.data +'&nbsp;&nbsp;&nbsp;&nbsp;+'+ p.bani + '<li>';
                listHtml = listHtml + el;
            }
            if(p.tip == 1){
                var el = '<li style="list-style-type: none">' + p.data +'&nbsp;&nbsp;&nbsp;&nbsp;-'+ p.bani + '<li>';
                listHtml=listHtml+el;

            }

        }

    }
    list.innerHTML = listHtml;
}


function listOfProducts1() {
    $.ajax({
        url: 'productsurl?action=list'
    }).done(function (response) {
        listProductsUL1(response.productsjson);
    });
}


function insertProduct() {
    var bani =document.getElementById('bani').value ;
    $.ajax({
        url: 'productsurl?action=insert&bani='+bani
    }).done(function (response) {
         location.href="meniu.html"
    });
}
function retragereProduct() {
    var bani =document.getElementById('bani').value ;
    $.ajax({
        url: 'productsurl?action=retragere&bani='+bani
    }).done(function (response) {
        location.href="meniu.html"
    });
}




