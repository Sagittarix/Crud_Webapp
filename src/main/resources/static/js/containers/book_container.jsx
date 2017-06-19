var BookContainer = React.createClass({

   getInitialState: function () {
       return {
           books: []
       }
   },

   componentWillMount: function () {
       this.refresh();
   },

   refresh: function () {
       var self = this;
       axios.get('/api/books')
           .then(function (response) {
               self.setState({books: response.data});
           })
           .catch(function (error) {
               if (error !== null) {
                   console.log(error.response);
               }
           });
   },

   handleRemoveBook: function (book) {
       var self = this;
       axios.delete('/api/books/' + book.id)
           .then(function (response) {
               if (response.status === 204) {
                   self.componentWillMount();
               } else {
                   alert("Problem!");
               }
           })
           .catch(function (error) {
               if (error !== null) {
                   console.log(error.response);
               }

           });
   },

   render: function () {

       var self = this;
       var modalId = "modal_add";
       var modalIdHash = "#modal_add";

       var bookList = this.state.books.map(function (
           book,
           index) {
           return (
               <tr key={index}>
                   <td>{book.title}</td>
                   <td>{book.author}</td>

                   <td>
                       <BookEditModalContainer book={book}
                                                reload={self.refresh}/>
                   </td>

                   <td>
                       <button type="button"
                               className="btn btn-danger"
                               onClick={self.handleRemoveBook.bind(
                                   self,
                                   book)}>
                           <span className="glyphicon glyphicon-remove"></span>
                       </button>
                   </td>

               </tr>
           );
       });

       return (
           <div>
               <div>
                   <BookAddModalContainer reload={self.refresh}/>
               </div>
               <br/>

               <div className="panel panel-default">
                   <table className="table table-hover">
                       <thead>
                       <tr>
                           <th>Title</th>
                           <th>Author</th>
                           <th>Edit</th>
                           <th>Delete</th>
                       </tr>
                       </thead>
                       <tbody>
                       {bookList}
                       </tbody>
                   </table>
               </div>
           </div>
       )
   }
});

window.BookContainer = BookContainer;

