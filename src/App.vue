<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import Header from './components/Header'
// import Footer from './components/Footer'
export default {
  name: 'App'
  // components: {
  //   'v_Header': Header,
  //   'v_Footer': Footer
  // }
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  .background_pic {
    width: 1280px;
  }
</style>
