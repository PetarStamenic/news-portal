<template>
  <div>
    <UserCom :korisnici="user"></UserCom>
  </div>
</template>

<script>
import UserCom from "../components/UserCom";
export default {
  name: "SingleUser",

  components: {UserCom},
  data() {
    return {
      user: {},
    }
  },
  created() {
    this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/users/${this.$route.params.email}`,{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.user = response.data;
    });
  }
}
</script>

<style scoped>

</style>
