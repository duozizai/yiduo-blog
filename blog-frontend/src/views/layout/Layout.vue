<template>
  <div  >
     <navbar class="div-less"/>
     <!-- <leftbar  class="div-less"/> -->
     <main-app class="div-less"/>
     <flootbar />
  </div>
</template>
<script>
import Navbar from '@/views/layout/components/Navbar'
import Flootbar from '@/views/layout/components/Flootbar'
import MainApp from '@/views/layout/components/MainApp'
// import Leftbar from '@/views/layout/components/Leftbar'
// import { Navbar, Flootbar, MainApp, Leftbar } from './components'
export default {
  name: 'Layout',
  components: {
    Navbar,
    // Leftbar,
    MainApp,
    Flootbar
  }
}
</script>
<style >
body {
  margin: 0px;
}
.div-less {
    padding: 10px;
  }
 
</style>


