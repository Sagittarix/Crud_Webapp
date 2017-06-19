var BookAddModalContainer = React.createClass({

   getInitialState: function () {
       return {
           book: {}
       }
   },

   handleTitle: function (e) {
       var book = this.state.book;
       book.title = e.target.value;
       this.setState({book: book});
   },

   handleAuthor: function (e) {
       var book = this.state.book;
       book.author = e.target.value;
       this.setState({book: book});
   },

   handleAddBook: function () {
       var self = this;
       axios.post('/api/books', this.state.book)
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
                                   Book data form
                               </h4>
                           </div>

                           <div className="modal-body">
                               <form>
                                   <label>Title</label>
                                   <input id="title"
                                          className="form-control"
                                          type="text"
                                          onChange={self.handleTitle}
                                   /><br />

                                   <label>Author</label>
                                   <input id="author"
                                          className="form-control"
                                          type="text"
                                          onChange={self.handleAuthor}
                                   /><br />
                               </form>
                           </div>
                           <div className="modal-footer">
                               <button type="button"
                                       className="btn btn-xs btn-success"
                                       onClick={self.handleAddBook}>
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

window.BookAddModalContainer = BookAddModalContainer;

