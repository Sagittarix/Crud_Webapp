var LibraryAddModalContainer = React.createClass({
    getInitialState: function () {
     return {
         library: {}
     }
    },

    handleName: function (e) {
     var library = this.state.library;
        library.name = e.target.value;
     this.setState({library: library});
    },

    handleAdd: function () {
     var self = this;
     axios.post('/api/library', this.state.library)
         .then(function (response) {
             if (response.status === 201) {
                 $("#modal_add").modal('hide');
                 self.props.reload();
             }
         })
         .catch(function (error) {
             if (error !== null) {
                 console.log(error);
             }
         });
    },

    render: function () {
     var self = this;
     var modalId = "modal_add";
     var modalIdHash = "#modal_add";

     return (
         <span>
             <button type="button"
                     className="btn btn-success"
                     data-toggle="modal"
                     data-target={modalIdHash}>
                 <span className="glyphicon glyphicon-plus"></span>
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
                                 <label>Name</label>
                                 <input id="name"
                                        className="form-control"
                                        type="text"
                                        onChange={self.handleName}
                                 /><br />
                             </form>
                         </div>
                         <div className="modal-footer">
                             <button type="button"
                                     className="btn btn-xs btn-success"
                                     onClick={self.handleAdd}>
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
         </span>
     )
    }
    });

window.LibraryAddModalContainer = LibraryAddModalContainer;

