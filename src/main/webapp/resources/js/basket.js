$(document).ready(function(){
    var basket_view = {

        initialize: function () {
            this.init();
            this.setupListeners();
        },

        setupListeners: function () {
            basket_view.listener.onClickBuyButton();
        },

        init: function () {
            basket_view.data.getProducts();
        },

        listener: {
            onClickBuyButton: function () {
                $("#buy").on('click',function (event) {
                    event.preventDefault();

                    var selecteditems = [];

                    $(".products").each(function (i, ob) {
                        selecteditems.push($(ob).val());
                    });

                    $.post("/mystore/buyService", { 'ids': selecteditems.toString() } , function(responseJson) {
                        console.log('success');
                    }).done(function(response) {
                        console.log('done')
                    }).fail(function(response) {
                        console.log("error" + response.status);
                    }).always(function(response) {

                        if (response.status === 201) {
                            window.location.href = "/mystore/shop/success";
                        }
                        if (response.status === 400) {
                            window.location.href = "/mystore/shop/failure";
                        }
                        else if (response.status === 500) {
                            window.location.href = "/mystore";
                        }
                    });
                });
            }
        },

        data: {
            getProducts: function() {
                $.post("/mystore/shop/basket" + window.location.search, function(response) {

                    var products = "";

                    $.each(response.products, function(index, product) {
                        products
                            += '<div class="card' + (product == null ? ' darkred-border' : '') + '">'
                            + ' <h5 class="card-header">' + (product == null ? '' : product.name) + '</h5>'
                            + ' <div class="card-body">'
                            + '     <h5 class="card-title">' + (product == null ? index : product.id) + '</h5>'
                            + '     <p class="card-text">' + (product == null ? 0 : product.price) + ' $</p>'
                            + (product == null ? '' : '         <input type="hidden" class="products" value="' + product.id + '" readonly>')
                            + ' </div>'
                            + '</div>';
                    });

                    $("#cards").append(products);
                    $('<div class="alert alert-primary" role="alert"><p class="mb-0 text-right">Total: ' + response.sum + '</p></div>').insertBefore( "#cards" );
                }).done(function(response) {
                    console.log('done')
                }).fail(function(response) {
                    console.log("error" + response.status);
                }).always(function(response) {

                });

                $("#container").append('<button id="buy" type="button" class="btn btn-primary active my-3 right">Buy</button>');
            }
        }
    };

    basket_view.initialize();
});