<template>
  <div class="news" style="text-align: center">
    <h1 class="mt-4">News</h1>

    <div class="row" style="display:inline;">
      <div class="col-4 mx-auto" >
        <table class=" table text-center" style="width: 650px;margin-left: -150px;">

          <thead>
          <tr>
            <th scope="col">Title</th>
            <th scope="col">Created At</th>
            <th scope="col">Content</th>
          </tr>
          </thead>

          <tbody >

          <tr v-for="news in newsList" :key="news.id" @click="find(news.id)">

            <b-card style="margin-top: 10px">
            <td scope="row"> {{ news.title }}</td>
            </b-card>

            <td>{{new Date(news.creation_date).toISOString().split('T')[0] }}</td>
            <td>{{ news.content | shortText }}</td>

          </tr>

          </tbody>

        </table>
      </div>
      <div class="col-6" >
        <NewsCom v-if="selectedNews" :news="selectedNews"></NewsCom>
      </div>
    </div>
  </div>
</template>

<script>
import NewsCom from "../components/NewsCom";

export default {
  components: {NewsCom},
  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      selectedNews: null,
      newsList: []
    }
  },
  methods: {
    find(id) {
        this.$router.push(`/news/${id}`);
    }
  },
  mounted() {
    this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/news/tag/${this.$route.params.id}`,{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.newsList = response.data;
    });
  }
}
</script>
