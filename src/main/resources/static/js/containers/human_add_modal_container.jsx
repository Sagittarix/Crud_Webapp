var HumanAddModalContainer = React.createClass({

   getInitialState: function () {
       return {
           human: {},
           name: '',
           surname: '',
           age: 0
       }
   },

   handleName: function (e) {
       console.log(e.target.value);
       this.setState({name: e.target.value});
   },

   handleSurname: function (e) {
       console.log(e.target.value);
       this.setState({surname: e.target.value});
   },

   handleAge: function (e) {
       console.log(e.target.value);
       this.setState({age: e.target.value});
   },

   handleAddHuman: function () {
       var self = this;
       console.log("new");

       this.state.human.name = this.state.name;
       this.state.human.surname = this.state.surname;
       this.state.human.age = this.state.age;

       console.log(this.state.human);

       axios.post('/api/human', this.state.human)
           .then(function (response) {
               console.log(response);
               if (response.status == 201) {
                   $( "#modal_add").modal( 'hide' );
                   self.props.reload();
               }
           })
           .catch(function (error) {
               if(error != null) {
                   console.log(error.response);
               }

           });
   },

   render: function () {

       var self = this;
       var modalId = "modal_add";
       var modalIdHash = "#modal_add";

       return (
           <div>
               <button type="button"
                       className="btn btn-success"
                       data-toggle="modal"
                       data-target={modalIdHash}>
                   ADD HUMAN
               </button>

               <div className="modal fade"
                    id={modalId}
                    tabIndex="1"
                    role="dialog"
                    aria-labelledby="myModalLabel"
                    aria-hidden="true">

                   <div className="modal-dialog modal-lg">
                       <div className="modal-content">
                           <div className="modal-header">

                               <button type="button"
                                       className="close"
                                       data-dismiss="modal">
                                   <span aria-hidden="true">&times;</span>
                                   <span className="sr-only">Close</span>
                               </button>

                               <h4 className="modal-title"
                                   id="myModalLabel">
                                   Human data form
                               </h4>
                           </div>

                           <div className="modal-body">


                               <form>

                                   <label>Name</label><br />
                                   <input id="name"
                                          className="form-control"
                                          type="text"
                                          onChange={self.handleName}
                                   /><br />

                                   <label>Surname</label><br />
                                   <input id="surname"
                                          className="form-control"
                                          type="text"
                                          onChange={self.handleSurname}
                                   /><br />

                                   <label>Age</label><br />
                                   <input id="age"
                                          className="form-control"
                                          type="number"
                                          onChange={self.handleAge}
                                   /><br />

                               </form>


                           </div>
                           <div className="modal-footer">
                               <button type="button"
                                       className="btn btn-xs btn-success"
                                       onClick={self.handleAddHuman}>
                                   Submit
                               </button>
                               <button type="button"
                                       className="btn btn-xs btn-danger"
                                       data-dismiss="modal">
                                   Cancel
                               </button>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       )
   }
});

window.HumanAddModalContainer = HumanAddModalContainer;

