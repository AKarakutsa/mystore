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

                    $.post("/mystore/mystore/buyService", { 'ids': selecteditems.toString() } , function(responseJson) {
                        console.log('success');
                    }).done(function(response) {
                        console.log('done')
                    }).fail(function(response) {
                        console.log("error" + response.status);
                    }).always(function(response) {

                        if (response.status === 201) {
                            window.location.href = "/mystore/mystore/shop/success";
                        }
                        if (response.status === 400) {
                            window.location.href = "/mystore/mystore/shop/failure";
                        }
                        else if (response.status === 500) {
                            window.location.href = "/mystore/mystore";
                        }
                    });
                });
            }
        },

        data: {
            getProducts: function() {
                $.post(window.location.href, function(responseJson) {

                    var products = "";

                    $.each(responseJson.products, function(index, product) {
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
                    $('<div class="alert alert-primary" role="alert"><p class="mb-0 text-right">Total: ' + responseJson.sum + '</p></div>').insertBefore( "#cards" );
                });

                $("#container").append('<button id="buy" type="button" class="btn btn-primary active my-3 right">Buy</button>');
            }
        }
    };

    basket_view.initialize();
});