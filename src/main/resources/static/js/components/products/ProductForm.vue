<template>
    <div>
        <input type="text" placeholder="name" v-model="name">
        <input type="text" placeholder="price" v-model="price">
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    function getIndex(list, id) {

        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id)
                return i;
        }

        return -1;
    }

    export default {
        name: "ProductForm",
        props: ['products', 'currentProduct'],
        data() {
            return {
                id: '',
                name: '',
                price: ''
            }
        },
        watch: {
            currentProduct(newVal, oldVal) {
                this.id = newVal.id;
                this.name = newVal.name;
                this.price = newVal.price;
            }
        },
        methods: {
            save() {
                var product = {
                    name: this.name,
                    price: this.price
                };

                if (this.id) {
                    this.$resource('/products{/id}').update({id: this.id}, product).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.products, data.id);
                            this.products.splice(index, 1, data);
                        })
                    );
                } else {
                    this.$resource('/products{/id}').save({}, product).then(result =>
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
</script>

<style scoped>

</style>