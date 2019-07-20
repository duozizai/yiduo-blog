<template>
   <div class="flooder">{{ msg }}</div>
</template>

<script>
export default {
  name: 'Flootbar',
  data () {
    return {
      msg: 'my blog'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.flooder {
  padding: 10px;
  background-color: black;
  color: white;
  text-align: center;
  width: 100%;
  bottom: 0px;
  position: fixed;
}
</style>
