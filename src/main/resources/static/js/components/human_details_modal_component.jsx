var HumanDetailsModalComponent = React.createClass({
   render: function () {

       var self = this;
       var human = self.props.human;
       var humanId = human.id;
       var modalId = "modal_details" + humanId;
       var modalIdHash = "#modal_details" + humanId;

       return (
           <div>
               <button type="button"
                       className="btn btn-success"
                       data-toggle="modal"
                       data-target={modalIdHash}>
                   <span className="glyphicon glyphicon-eye-open"></span>
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
                                   <span
                                       aria-hidden="true">&times;</span>
                                   <span
                                       className="sr-only">Close</span>
                               </button>

                               <h4 className="modal-title"
                                   id="myModalLabel">
                                   Human details
                               </h4>
                           </div>

                           <div className="modal-body">
                               <div className="panel panel-default">
                                   <table
                                       className="table table-hover">
                                       <thead>
                                       </thead>
                                       <tbody>
                                       <tr>
                                           <td>
                                               Name:
                                               <b>{human.name}</b>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td>
                                               Surname:
                                               <b>{human.surname}</b>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td>
                                               Age:
                                               <b>{human.age}</b>
                                           </td>
                                       </tr>
                                       </tbody>
                                   </table>
                               </div>
                           </div>
                           <div className="modal-footer">
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

window.HumanDetailsModalComponent = HumanDetailsModalComponent;

