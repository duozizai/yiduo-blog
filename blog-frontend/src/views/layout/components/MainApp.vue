<template>
  <div class="main">
  <router-view :key="key"/>
  </div>
</template>
<script>
export default {
  name: 'MainApp',
  computed: {
    key () {
    //   console.log(this.$route.fullPath)
      return this.$route.fullPath
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style   scoped>

</style>
