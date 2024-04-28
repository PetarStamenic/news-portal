<template>
  <div class="pt-5">
    <form method="post" v-on:submit.prevent = "postCategory()" >
      <div class="form-group">
        <label for="ime">Title</label>
        <input style="margin-top: 10px;" required  v-model="name" type="text" class="form-control" id="ime" placeholder="Enter name">
      </div>
      <div class="form-group">
        <label for="content" style="margin-top: 10px;">Content</label>
        <input style="margin-top: 10px;" required  v-model="description" type="text" class="form-control" id="content" placeholder="Enter desc">
      </div>
      <br>
      <button type="submit" class="btn btn-primary mt-2">Add Category</button>
    </form>

    <h1 class="mt-4">Category</h1>
    <div class="row" style="display:inline;">
      <div class="col-4 mx-auto" >
        <table class=" table text-center" style="width: 650px;margin-left: -150px;">
          <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Delete</th>
            <th scope="col">Edit</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="category in categoryList" :key="category.name">
            <b-card style="margin-top: 10px">
              <td scope="row" @click="findCategory(category.name)"> {{ category.name }}</td>
            </b-card>
            <td scope="row"> {{ category.description }}</td>
            <td scope="row">
              <b-button v-if=" findNumberOfNews(category.name)" @click="deleteCategory(category.name)" size="sm">Delete</b-button>
            </td>
            <td>
              <b-button @click="editCategory(category.name)">Edit</b-button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "AddCategory",
  data() {
    return {
      date: '',
      name: null,
      description: null,
      categoryList: [],
    }
  },
  mounted() {
    this.$axios.get('http://localhost:8080/projekat_backend_war_exploded/api/category',{headers: {
        Authorization: "Bearer " + localStorage.getItem('jwt')
      }}).then((response) => {
      this.categoryList = response.data;
    })
  },
  methods: {
    postCategory() {
      console.log(this.name);
      console.log(this.description)
      let alreadyExists = 0;
      for (let i = 0; i < this.categoryList.length; i++) {
        if (this.categoryList[i].name === this.name) {
          alreadyExists = 1;
        }
      }
      if (alreadyExists === 1) this.$alert("Category with that name already exists")
      else {
        this.$axios.post('http://localhost:8080/projekat_backend_war_exploded/api/category',{
          "name": this.name,
          "description": this.description} ,{headers: {
            Authorization: "Bearer " + localStorage.getItem('jwt')
          }
        }).then(() => {
          window.location.reload()
        })
      }
    },
    findCategory(name) {
      this.$router.push(`/category/${name}`);
    },
    findNumberOfNews(name) {
      var value = 0;
      this.$axios.get(`http://localhost:8080/projekat_backend_war_exploded/api/news/category/${name}`,{headers: {
          Authorization: "Bearer " + localStorage.getItem('jwt')
        }}).then((response) => {
        this.newsList = response.data;
        value = this.newsList.length;
        console.log(value === 0)
        return value === 0
      })
      return true

    },
    editCategory(name) {
      this.$router.push(`/category/${name}`)
    },
    deleteCategory(name) {
      this.$axios.delete(`http://localhost:8080/projekat_backend_war_exploded/api/category/${name}`,{headers: {
          Authorization: "Bearer " + localStorage.getItem('jwt')
        }}).then(() => {
        this.categoryList.splice(name, 1);
      });
      window.location.reload()
    },
  }
}
</script>

<style scoped>

</style>
