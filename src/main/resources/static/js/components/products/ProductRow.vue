<template>
    <div>
        <b>{{product.id}}</b> {{product.name}} {{product.price}}
        <span style="position: absolute; right: 0;">
       <input type="button" value="Edit" @click="edit"/>
       <input type="button" value="X" @click="del"/>
       </span>
    </div>
</template>

<script>
    export default {
        name: "ProductRow",
        props: ['products', 'product', 'editProduct', 'deleteProduct'],
        methods: {
            edit() {
                this.editProduct(this.product)
            },
            del() {
                this.deleteProduct(this.product)
            }
        }
    }
</script>

<style scoped>

</style>