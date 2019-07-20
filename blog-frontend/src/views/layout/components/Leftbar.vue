<template>
  <div class="lefter ">
            <ul class="left-ul">
              <li v-for="item in items" 
              @click="getPath(item.path)" 
              :class="{'router-link-exact-active' 
              :selected == item.path}">
                <span > {{ item.name }}</span>
              </li>
                <!-- <li @click="getHome">首页</li>
                <li @click="goIndex">index</li>
                <li @click="goDashboard">标题3</li>  -->
            </ul>
        </div>
</template>

<script>
export default {
  name: 'Leftbar',
  data () {
    return {
      msg: '我是左侧',
      items: [
        {
          name: '首页',
          path: '/index'
        },
        {
          name: 'blog',
          path: '/blogs'
        },
        {
          name: 'user',
          path: '/users'
        }
      ],
      selected: ''
    }
  },
  mounted () {
    // console.log(123)
    // console.log(this.$route.fullPath)
    this.selected = this.$route.fullPath
  },
  methods: {
    getPath (pathStr) {
      // console.log(this.$route.fullPath)
      this.selected = pathStr
      this.$router.push({ path: pathStr })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style   scoped>
/* @import "src/styles/main.css"; */
.lefter {
  width: 350px;
  float: left;
  /* background-color: aqua; */
}
.lefter .left-ul {
  list-style-type: none;
  margin: 0px;
  padding-left: 0px;
  text-align: center;
}
.lefter .left-ul li {
  padding: 2px;
}
li {
  width: 350px;
  list-style: none;
  line-height: 2rem;
  color: black;
  transition: background-color 1s linear, color 1s linear;
  -webkit-transition: background-color 1s linear, color 1s linear;
  -moz-transition: background-color 1s linear, color 1s linear;
  -o-transition: background-color 1s linear, color 1s linear;
}
.router-link-exact-active {
  background-color: #ff3d67;
}
li:hover {
  /* background-color: #ff3d67; */
  color: blue;
}
</style>
