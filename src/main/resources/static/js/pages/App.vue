<template>
    <div>
        <div v-if="!profile">Войти через <a href="/login">Google</a></div>
        <div v-else><a href="/logout"> Выйти </a></div>
        <product-list :products="products"/>
    </div>
</template>

<script>
    import ProductList from 'components/products/ProductList.vue'
    export default {
        name: 'App',
        components:{
          ProductList
        },
        data() {
            return {
                products: frontentData.products,
                profile: frontentData.profile
            }
        }
    }
</script>

<style scoped>

</style>