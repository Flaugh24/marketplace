<template>
    <div style="position: relative; width: 300px">
        <product-form :products="products" :currentProduct="product"/>
        <product-row v-for="product in products"
                     :key="product.id"
                     :products="products"
                     :product="product"
                     :editProduct="editProduct"
                     :deleteProduct="deleteProduct"/>
    </div>
</template>

<script>
    import ProductForm from 'components/products/ProductForm.vue'
    import ProductRow from 'components/products/ProductRow.vue'

    export default {
        name: "ProductList",
        props: ['products'],
        components: {
            ProductRow,
            ProductForm
        },
        data() {
            return {
                product: null
            }
        },
        methods: {
            editProduct(product) {
                this.product = product
            },
            deleteProduct(product) {
                this.$resource('/products{/id}').remove({id: product.id}).then(result => {
                        if (result.ok) {
                            this.products.splice(this.products.indexOf(product), 1)
                        }
                    }
                );
            }
        }
    }
</script>

<style scoped>

</style>