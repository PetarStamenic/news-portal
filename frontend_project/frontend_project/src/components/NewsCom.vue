<template>
  <div>
    <h2>
      Title: {{ news.title }}
    </h2>
    <h6 style="margin-top: 18px">
      Category: {{news.category}}
    </h6>

    <div v-if="tags">
      <h6 style="margin-top: 12px">Tags:</h6>
      <h6 v-for="tag in tags" :key = "tag.id" @click="toTag(tag.id)">
        {{tag.tag_name}}
      </h6>
    </div>
    <h5  style="margin-top: 12px">
      Author: {{ news.author.last_name.toString() +" "+news.author.email.toString() }}
    </h5>

    <h6  style="margin-top: 12px">
      Creation date: {{new Date(news.creation_date).toISOString().split('T')[0] }}
    </h6>
    <br>
    <h4>Content:</h4>
    <p>
      {{ news.content }} <br><br>
    </p>
    <br>
    <h2>Add new comment</h2>
    <br>
    <form method="post" v-on:submit.prevent = "postComment()" >
      <div class="form-group">
        <label for="name">Name</label>
        <input required v-model="author" type="text" class="form-control" id="name" placeholder="Enter name">
      </div>
      <br>

      <div class="form-group">
        <label for="comment">Comment</label>
        <input required v-model="content" type="text" class="form-control" id="comment" placeholder="Comment">
      </div>
      <br>
      <button type="submit" class="btn btn-primary mt-2">Share</button>
    </form>

    <br> <br>
    <h3 style="margin-top: 12px">Comments:</h3>
    <h6 v-for="comment in comments" :key = "comment.id">
      Author:
      {{comment.author}}
      <br>
      {{new Date(comment.creation_date).toISOString().split('T')[0] }}
      <br>
      <b-card style="margin-top: 10px">
        <h4 style="margin-top: 15px;margin-left: 20px">{{comment.content}}</h4>
      </b-card>
    </h6>

  </div>
</template>

<script>

export default {
  name: "NewsCom",
  props: {
    news: {
      type: Object,
      required: true,
    }
  },
  data() {
    return {
      tags: [],
      comments: [],
      author: null,
      content: null,
      creationDate: null,
    }
  },
  mounted() {
    this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/news/newsTag/${this.$route.params.id}`,{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.tags = response.data;
    })
    this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/news/comments/${this.$route.params.id}`,{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.comments = response.data;
    })
  },
  methods: {
    postComment(){
      this.$axios.post(`http://localhost:8080/projekat_backend_war_exploded/api/comments`, {
        "author": this.author,
        "content": this.content,
        "news": this.news
      }).then(() => {
        window.location.reload();
      });
    },
    toTag(id) {
      this.$router.push(`/news/tag/${id}`);
      console.log(id)
    }
  }
}
</script>

<style scoped>

</style>
