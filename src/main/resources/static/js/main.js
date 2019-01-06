var productApi = Vue.resource('/products{/id}');

function getIndex(list, id) {

    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id)
            return i;
    }

    return -1;
}

Vue.component('product-form', {
        props: ['products', 'currentProduct'],
        data: function () {
            return {
                id: '',
                name: '',
                price: ''
            }
        },
        watch: {
            currentProduct: function (newVal, oldVal) {
                this.id = newVal.id;
                this.name = newVal.name;
                this.price = newVal.price;
            }
        },
        template:
            '<div>' +
            '<input type="text" placeholder="name" v-model="name">' +
            '<input type="text" placeholder="price" v-model="price">' +
            '<input type="button" value="Save" @click="save"/>' +
            '</div>',
        methods: {
            save: function () {
                var product = {
                    name: this.name,
                    price: this.price
                };

                if (this.id) {
                    productApi.update({id: this.id}, product).then(result =>
                        result.json().then(data => {
                            var index = getIndex(this.products, data.id);
                            this.products.splice(index, 1, data);
                        })
                    );
                } else {
                    productApi.save({}, product).then(result =>
                        result.json().then(data => {
                            this.products.push(data);
                        })
                    );
                }

                this.id = '';
                this.name = '';
                this.price = '';
            }
        }
    }
);

Vue.component('product-row', {
    props: ['products', 'product', 'editMethod'],
    template: '<div>' +
        '<b>{{product.id}}</b> {{product.name}} {{product.price}}' +
        '<span style="position: absolute; right: 0;">' +
        '<input type="button" value="Edit" @click="edit"/>' +
        '<input type="button" value="X" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.product);
        },
        del: function () {
            productApi.remove({id: this.product.id}).then(result => {
                    if (result.ok) {
                        this.products.splice(this.products.indexOf(this.product), 1)
                    }
                }
            );
        }
    }
});


Vue.component('products-list', {
    props: ['products'],
    data: function () {
        return {
            product: null
        }
    },
    template:
        '<div style="position: relative; width: 300px">' +
        '<product-form :products="products" :currentProduct="product"/>' +
        '<product-row  v-for="product in products" :key="product.id" ' +
        ':products="products" :product="product" :editMethod="editProduct"/>' +
        '</div>',
    methods: {
        editProduct: function (product) {
            this.product = product;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '' +
        '<div>' +
        '<div v-if="!profile">Войти через <a href="/login" >Google</a></div>' +
        '<div v-else><a href="/logout"> Выйти  </a></div>' +
        '<products-list :products="products"/>' +
        '</div>',
    data: {
        products: [],
        profile: frontentData.profile
    },
    created: function () {
        productApi.get().then(result =>
            result.json().then(data =>
                data.forEach(product => this.products.push(product))
            )
        )
    }
});