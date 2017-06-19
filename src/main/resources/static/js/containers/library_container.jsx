var LibraryContainer = React.createClass({

   getInitialState: function () {
       return {
           library: {}
       }
   },

   componentWillMount: function () {
       this.refresh();
   },

   refresh: function () {
       var self = this;
       axios.get('/api/library/1')
           .then(function (response) {
               self.setState({library: response.data});
           })
           .catch(function (error) {
               if (error !== null) {
                   console.log(error.response);
               }
           });
   },

   handleRemoveFromLibrary: function (book) {
       var self = this;
       axios.post('/api/library/d', book)
           .then(function (response) {
               self.componentWillMount();
           })
           .catch(function (error) {
               if (error != null) {
                   console.log(error);
               }
           });
   },

   render: function () {
       var self = this;
       var library = self.state.library;
       var books;
       if(this.state.library.books != null) {
           books = this.state.library.books.map(function (book, index) {
               return (
                   <tr key={index}>
                       <td>{book.title}</td>
                       <td>{book.author}</td>
                       <td>
                           <button type="button"
                                   className="btn btn-danger"
                                   onClick={self.handleRemoveFromLibrary.bind(self, book)}>
                               <span className="glyphicon glyphicon-remove"></span>
                           </button>
                       </td>
                   </tr>
               );
           });
       }


       var create;
       if(library.name == null) {
           create = <LibraryAddModalContainer reload={self.refresh}/>;
       }


       return (
           <div>
               <div>
                   <LibraryEditModalContainer lib={library} reload={self.refresh}/>
                   {create}
               </div>
               <br/>

               <h1>Library: {library.name}</h1>
               <br />

               <h2>Library's books:</h2>
               <br />


               <div className="panel panel-default">
                   <table className="table table-hover">
                       <thead>
                       <tr>
                           <th>Title</th>
                           <th>Author</th>
                           <th>Remove</th>
                       </tr>
                       </thead>
                       <tbody>
                       {books}
                       </tbody>
                   </table>

               </div>
           </div>
       )
   }
});

window.LibraryContainer = LibraryContainer;

