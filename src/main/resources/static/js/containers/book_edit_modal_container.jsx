var BookEditModalContainer = React.createClass({

    getInitialState: function () {
        return {
            book: {}
        }
    },

    componentWillMount: function () {
        var book = this.props.book;
        this.setState({book: book});
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
                    $("#modal_add" + self.state.book.id).modal('hide');
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
        var book = self.state.book;
        var modalId = "modal_add" + book.id;
        var modalIdHash = "#modal_add" + book.id;

        return (
            <div>
                <button type="button"
                        className="btn btn-warning"
                        data-toggle="modal"
                        data-target={modalIdHash}>
                    <span className="glyphicon glyphicon-edit"></span>
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
                                    <span className="sr-only">Close</span>
                                </button>

                                <h4 className="modal-title"
                                    id="myModalLabel">
                                    Human data form
                                </h4>
                            </div>

                            <div className="modal-body">
                                <form>
                                    <label>Title</label>
                                    <input id="name"
                                           className="form-control"
                                           type="text"
                                           value={self.state.book.title}
                                           onChange={self.handleTitle}
                                    /><br />

                                    <label>Author</label>
                                    <input id="surname"
                                           className="form-control"
                                           type="text"
                                           value={self.state.book.author}
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
            </div>
        )
    }
});

window.BookEditModalContainer = BookEditModalContainer;

