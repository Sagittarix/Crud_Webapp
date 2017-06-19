var BookContainer = React.createClass({

   getInitialState: function () {
       return {
           books: [],
           actionList: []
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
               self.setState({actionList: []});
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
               if (error != null) {
                   console.log(error);
               }

           });
   },

    handleAddToLibrary: function() {
    var self = this;
    axios.post('/api/library/1', self.state.actionList)
        .then(function (response) {
            if (response.status === 201) {
                self.context.router.push( '/library' );
            }
        })
        .catch(function (error) {
            if (error != null) {
                console.log(error);
            }
        });
    },

    handleChecked: function (book) {
       var list = this.state.actionList;
        if(list.filter(b => b === book).length > 0) {
            list = list.filter(b => b !== book);
        } else {
            list.push(book);
        }
        this.setState({actionList: list});
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
                               onClick={self.handleRemoveBook.bind(self, book)}>
                           <span className="glyphicon glyphicon-remove"></span>
                       </button>
                   </td>
                    <td>
                        <input className="check-box"
                               id="checkBox"
                               type="checkbox"
                               onClick={self.handleChecked.bind(self, book)} />
                   </td>
               </tr>
           );
       });

       return (
           <div>
               <div>
                   <BookAddModalContainer reload={self.refresh}/>
                   <span> </span>
               <button type="button"
                       className="btn btn-primary"
                       onClick={self.handleAddToLibrary}>
                   <span>Add to library</span>
               </button>
               </div>
               <br />

               <div className="panel panel-default">
                   <table className="table table-hover">
                       <thead>
                       <tr>
                           <th>Title</th>
                           <th>Author</th>
                           <th>Edit</th>
                           <th>Delete</th>
                           <th>Select</th>
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

BookContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.BookContainer = BookContainer;

