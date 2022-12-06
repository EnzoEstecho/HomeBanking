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
                cards: [],
                debit: [],
                name: "",
                credit: [],
                selectedColor: "",
                options: [
                    { text: "SILVER", value: "SILVER" },
                    { text: "TITANIUM", value: "TITANIUM" },
                    { text: "GOLD", value: "GOLD" },
                ],
                selectedType: "",
                options: [
                    { value: "DEBIT", text: "DEBIT" },
                    { text: "CREDIT", value: "CREDIT" },
                ],

            }
        },

        created() {
            axios
                .get("/api/clients/current")
                .then((response) => {
                    this.clients = response.data;
                    this.cards = this.clients.cards;
                    console.log(this.cards);
                    this.debit = this.cards.filter((card) => card.cardType == "DEBIT");
                    console.log(this.debit);
                    this.credit = this.cards.filter((card) => card.cardType == "CREDIT");
                    console.log(this.credit);
                    this.name = this.clients.firstName + " " + this.clients.lastName;
                })
                .catch(function (error) {
                    console.log(error);
                });


        },

        methods: {
            newDate(creationDate) {
                return new Date(creationDate).toLocaleDateString('en-US', { month: '2-digit', year: '2-digit' });
            },

            logOut() {
                axios.post('/api/logout')
                    .then(response => console.log('signed out!!!'))
            },


            createCards() {
                axios
                    .post(
                        "/api/clients/current/cards",
                        `cardType=${this.selectedType}&cardColor=${this.selectedColor}`
                    )
                    .then((response) => window.location.href=("./cards.html"))

                    .catch((response) => Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "You already have too many cards!",
                    }));
            },




        },
    }).mount('#app')