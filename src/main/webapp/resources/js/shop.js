$(document).ready(function(){
    var shop_view = {

        initialize: function () {
            this.init();
            this.setupListeners();
        },

        setupListeners: function () {
            shop_view.listener.onClickToBasketButton();
        },

        init: function () {
            shop_view.data.getProducts();
        },

        listener: {
            onClickToBasketButton: function () {
                $("#basket").on('click',function (event) {
                    event.preventDefault();

                    var selecteditems = [];

                    $(".form-check-input:checked").each(function (i, ob) {
                        selecteditems.push($(ob).val());
                    });

                    window.location.href = '/mystore/mystore/shop/basket?ids=' + selecteditems;
                });
            }
        },

        data: {
            getProducts: function() {
                $.post("/mystore/mystore/shop/items", function(responseJson) {

                    var products = "";

                    $.each(responseJson, function(index, product) {    // Iterate over the JSON array.
                        products
                            += '<div class="card">'
                            + ' <h5 class="card-header">' + product.name + '</h5>'
                            + ' <div class="card-body">'
                            + '     <h5 class="card-title">' + product.id + '</h5>'
                            + '     <p class="card-text">' + product.price + ' $</p>'
                            + '     <div class="form-check">'
                            + '         <input type="checkbox" class="form-check-input" value="' + product.id + '">'
                            + '         <label class="form-check-label">Check</label>'
                            + '     </div>'
                            + ' </div>'
                            + '</div>';
                    });

                    $("#cards").append(products);
                });

                $("#container").append('<button id="basket" type="button" class="btn btn-primary active my-3 right mr-3">To Basket</button>');
            }
        }
    };

    shop_view.initialize();
});