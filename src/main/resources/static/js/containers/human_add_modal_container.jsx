var HumanAddModalContainer = React.createClass({

   getInitialState: function () {
       return {
           human: {},
       }
   },

   componentWillMount: function () {
       var humanMock = {
           name: "aaa",
           surname: "bbb",
           age: 9,
           id: null
       };

       this.setState({human: humanMock});
   },

   handleName: function (e) {
       var humanName = this.state.human;
       humanName.name = e.target.value;
       this.setState({human: humanName});
   },

   handleSurname: function (e) {
       var humanSurname = this.state.human;
       humanSurname.surname = e.target.value;
       this.setState({human: humanSurname});
   },

   handleAge: function (e) {
       var humanAge = this.state.human;
       humanAge.age = e.target.value;
       this.setState({human: humanAge});
   },

   handleAddHuman: function () {
       var self = this;
       axios.post('/api/human', this.state.human)
           .then(function (response) {
               console.log(response);
               if (response.status === 201) {
                   $( "#modal_add").modal( 'hide' );
                   self.props.reload();
               }
           })
           .catch(function (error) {
               if(error !== null) {
                   console.log(error);
               }
           });
   },


    //modal takes in human
    //if human.id is null then create - add button
    //if human.id is not null edit - edit button

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
                                          value={self.state.human.name}
                                          onChange={self.handleName}
                                   /><br />

                                   <label>Surname</label><br />
                                   <input id="surname"
                                          className="form-control"
                                          type="text"
                                          value={self.state.human.surname}
                                          onChange={self.handleSurname}
                                   /><br />

                                   <label>Age</label><br />
                                   <input id="age"
                                          className="form-control"
                                          type="number"
                                          value={self.state.human.age}
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

