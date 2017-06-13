var HumanAddModalContainer = React.createClass({

   getInitialState: function () {
       return {
           human: {}
       }

   },

   componentWillMount: function () {

    var human = this.props.man;
        if(human != null) {
            this.setState({human: human});
        }
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

   render: function () {
       var self = this;
       var modalActivateButton;
       var modalId;
       var modalIdHash;
       var humanState = self.state.human;

       if(humanState.id != null) {
           modalId = "modal_add" + humanState.id;
           modalIdHash = "#modal_add" + humanState.id;
           modalActivateButton = <button type="button" className="btn btn-warning" data-toggle="modal" data-target={modalIdHash}><span className="glyphicon glyphicon-edit"></span></button>;
       } else {
           modalId = "modal_add";
           modalIdHash = "#modal_add";
           modalActivateButton = <button type="button" className="btn btn-success" data-toggle="modal" data-target={modalIdHash}>ADD HUMAN</button>;
       }




       return (
           <div>
               {modalActivateButton}
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

                                   <label>Name</label>
                                   <input id="name"
                                          className="form-control"
                                          type="text"
                                          value={self.state.human.name}
                                          onChange={self.handleName}
                                   /><br />

                                   <label>Surname</label>
                                   <input id="surname"
                                          className="form-control"
                                          type="text"
                                          value={self.state.human.surname}
                                          onChange={self.handleSurname}
                                   /><br />

                                   <label>Age</label>
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

