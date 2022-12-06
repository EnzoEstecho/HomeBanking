const body = document.querySelector("body"),
    sidebar = body.querySelector("nav"),
    toggle = body.querySelector(".toggle");


toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});



let urlParams = new URLSearchParams(window.location.search);
let urlName = urlParams.get('id');

const app = Vue.
createApp({
    data() {
        return {
            message: "Hello Vue!",
            clients: [],
            cards: [],
            debit:[],
            credit:[],
            
        }
    },

    created(){ 
        axios.get ('/api/clients/current')
            .then((response) => {
                this.clients = response.data;
                this.cards = this.clients.cards;
                console.log(this.cards);
                this.debit = this.cards.filter(card => card.cardType == "DEBIT");
                console.log(this.debit);
                this.credit = this.cards.filter(card => card.cardType == "CREDIT");
                console.log(this.credit);
            }  )    
            .catch(function (error) {
                console.log(error);
            }
        );

            
    },

    methods: {
        newDate(creationDate){ 
            return  new Date(creationDate).toLocaleDateString('en-US', {month: '2-digit', year: '2-digit'});
        },

        logOut() {
            axios.post('/api/logout')
            .then(response => console.log('signed out!!!'))
          },
          
    },
}).mount('#app')