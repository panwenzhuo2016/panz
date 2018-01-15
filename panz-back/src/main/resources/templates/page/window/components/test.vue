<template>
    <div>
        我再弹出一个层
        <button v-on:click="openDiv" >btn</button>
    </div>
</template>

<script>
  export default {
    methods: {
      openDiv: function () {
        let promise = this.$root.$refs.hDialog.open({
          template: '<div>第二层了</div>'
        })
        promise.then(function (arg) {
          alert('第二层' + arg.type)
          arg.close()
        })
      }
    }
  }
</script>
