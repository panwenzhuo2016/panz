<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button v-on:click="openDialog" >openDialog</button>
  </div>
</template>

<script>
import test from './test'
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    openDialog: function () {
      let dialog = this.$root.$refs.hDialog
      let vDialog = dialog.open({
        template: '<div>第一个组件<test></test></div>',
        components: {
          test
        },
        width: 800
      })
      vDialog.then(function (arg) {
        alert('第一层' + arg.type)
        arg.close()
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
