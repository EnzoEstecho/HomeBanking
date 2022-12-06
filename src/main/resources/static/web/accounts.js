  const body = document.querySelector("body"),
  sidebar = body.querySelector("nav"),
  toggle = body.querySelector(".toggle");


toggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});



const app = Vue.

createApp({

  data() {

    return {
      clients: [],
      accounts: [],
      loans: [],

      accountType: 0,
      types: "",
      number: [],
      numberAcc: 0,
      accountNumber: "",
      accountCancel: 0,
      accountsTrue: [],


    }
  },


  created() {

    axios.get('/api/clients/current')
      .then((response) => {
        this.clients = response.data;
        console.log(this.clients);
        this.accounts = response.data.accounts;
        console.log(this.accounts);
        this.loans = response.data.loans;
      })
      .catch(function (error) {
        console.log(error);
      }
      );

  },




  methods: {


    logOut() {
      axios.post('/api/logout')
      .then(response => console.log('signed out!!!'))
    },


    // createAccount() {
    //   axios.post('/api/clients/current/accounts' )
    //   .then( window.location.reload())
    //   .catch(() =>
    //       Swal.fire({
    //         icon: "error",
    //         title: "Oops...",
    //         text: "You can't create more than 3 accounts!",
    //   })
    //   )
    // }

    createAccount() {
      axios
        .post(
          "/api/clients/current/accounts",
          `accountType=${this.accountType}`
        )
        .then((response) =>
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Account created successfully",
            showConfirmButton: false,
            timer: 1500,
          })
        )
        .then((x) => window.location.reload())
        .catch((response) =>
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "You can't create this account!",
          })
        );
    },

    selectAccountType(value) {
      this.accountType = value;
      // Swal.fire({
      //   position: 'center',
      //   icon: 'success',
      //   tittle: value + " " + "account selected",
      //   showConfirmButton: false,
      //   timer:1500
      // })
    },




  }


}).mount('#app')







