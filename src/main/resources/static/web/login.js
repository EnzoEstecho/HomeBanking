const app = Vue.createApp({
    data() {
      return {
        email: "",
        password: "",
      };
    },
  
    created() {},
    methods: {
      login() {
        axios
          .post("/api/login", `email=${this.email}&password=${this.password}`, {
          })
          .then(response => location.href = "/web/accounts.html")
          .catch (response => Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Wrong email or password! Please try again.',
            footer: '<a href="">Why do I have this issue?</a>'
          })
          );
      },

    },
  }).mount("#app");