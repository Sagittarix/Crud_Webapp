var HumanContainer = React.createClass({

   getInitialState: function () {
       return {
           humans: []
       }
   },

   componentWillMount: function () {
       var self = this;
       axios.get('/api/human')
           .then(function (response) {
               self.setState({humans: response.data});
           })
           .catch(function (error) {
               if(error !== null) {
                   console.log(error.response);
               }
           });
   },

   handleRemoveHuman: function (human) {
       var self = this;
       axios.delete('/api/human/' + human.id)
           .then(function (response) {
               console.log(response);
               if (response.status === 204) {
                   self.componentWillMount();
               } else {
                   alert("Problem!");
               }
           })
           .catch(function (error) {
               if(error !== null) {
                   console.log(error.response);
               }

           });
   },

   render: function () {

       var self = this;
       var modalId = "modal_add";
       var modalIdHash = "#modal_add";

       var humanList = this.state.humans.map(function (human, index) {
           return (
               <tr key={index}>
                   <td>{human.id}</td>
                   <td>{human.name}</td>
                   <td>{human.surname}</td>
                   <td>
                       <HumanDetailsModalComponent human={human} />
                   </td>

                   <td>
                       <HumanAddModalContainer man={human}
                                               reload={self.componentWillMount()} />
                   </td>

                   <td>
                       <button type="button"
                               className="btn btn-danger"
                               onClick={self.handleRemoveHuman.bind(self, human)}>
                           <span className="glyphicon glyphicon-remove"></span>
                       </button>
                   </td>

               </tr>
           );
       });

       return (
           <div>
               <div>
                   <HumanAddModalContainer man={null}
                                           reload={self.componentWillMount()} />
               </div>
               <br/>

               <div className="panel panel-default">
                   <table className="table table-hover">
                       <thead>
                       <tr>
                           <th>Id</th>
                           <th>Name</th>
                           <th>Surname</th>
                           <th>Details</th>
                           <th>Edit</th>
                           <th>Delete</th>
                       </tr>
                       </thead>
                       <tbody>
                       {humanList}
                       </tbody>
                   </table>
               </div>
           </div>
       )
   }
});

window.HumanContainer = HumanContainer;

