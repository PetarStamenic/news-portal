<template>
  <div>
    <NewsCom :news="news"></NewsCom>
  </div>
</template>

<script>
import NewsCom from "../components/NewsCom";
export default {
  name: "SingleNews",
  components: {NewsCom},
  data() {
    return {
      news: {},
    }
  },
  created() {
    this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/news/${this.$route.params.id}`,{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.news = response.data;
    });
  }
}
</script>

<style scoped>

</style>
