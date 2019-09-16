<template>
  <div class="home">
    <img alt="Todo logo" src="../assets/todo.png">
    <HelloWorld msg="Todo 앱을 이용해 주셔서 감사해요. 오늘도 힘차게 일해요!!"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  }
}
</script>
