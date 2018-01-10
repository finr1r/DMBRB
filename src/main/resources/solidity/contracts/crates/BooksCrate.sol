pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../storage/StorageAdapter.sol";
import "./IBooksCrate.sol";

/**
 * @title BooksCrate
 *
 * @dev This is contract that manage data related to the books. Books srotes in
 *  separate crate.
 */
contract BooksCrate is IBooksCrate, Mortal, StorageAdapter, Validator {

    StorageInterface.Book book;
    StorageInterface.Bytes32UIntMapping periodsOfBookRent;

    event LogAddBook(
        address indexed seller,
        bytes32 title,
        bytes32 author,
        bool isRentable,
        uint price,
        uint status
    );

    function BooksCrate(Storage _store, bytes32 _crate)
        StorageAdapter(_store, _crate)
        public
    {
        book.init("books");
        periodsOfBookRent.init("periodsOfBookRent");
    }

    /**
     * @dev Stores the book under '_key' key.
     *
     * @param _key - the key of given book
     * @param title - the title of the book
     * @param author - the author of the book
     * @param isRentable - represents whether book can be rented
     * @param price - the price of the book
     * @param status - the current book status
     *
     * @return 'true' if book was successfully stored
     */
    function addBook(bytes32 _key, bytes32 title, bytes32 author, bool isRentable, uint price, BookStatus status)
        public
        returns (bool)
    {
        store.set(book.title, _key, title);
        store.set(book.author, _key, author);
        store.set(book.isRentable, _key, isRentable);
        store.set(book.price, _key, price);
        store.set(book.status, _key, uint(status));
        store.set(book.owner, _key, msg.sender);
        LogAddBook(msg.sender, title, author, isRentable, price, uint(status));
        return true;
    }

    /**
     * @dev Returns the full info about book by given key.
     *
     * @return info about book
     */
    function getBook(bytes32 _key) public view returns (bytes32[2], bool, uint[2], address) {
        return store.get(book, _key);
    }

    function getBookTitle(bytes32 _key) external view returns (bytes32) {
        return store.get(book.title, _key);
    }

    function getBookAuthor(bytes32 _key) external view returns (bytes32) {
        return store.get(book.author, _key);
    }

    function getBookIsRentable(bytes32 _key) external view returns (bool) {
        return store.get(book.isRentable, _key);
    }

    function getBookPrice(bytes32 _key) external view returns (uint) {
        return store.get(book.price, _key);
    }

    function getBookStatus(bytes32 _key) external view returns (uint) {
        return store.get(book.status, _key);
    }

    function setBookStatus(bytes32 _key, BookStatus status) external returns (bool) {
        store.set(book.status, _key, uint(status));
        return true;
    }

    function getBookOwner(bytes32 _key) external view returns (address) {
        return store.get(book.owner, _key);
    }

    function setBookOwner(bytes32 _key, address newOwner)
        addressIsNotNull(newOwner)
        external
        returns (bool)
    {
        store.set(book.owner, _key, newOwner);
        return true;
    }

    function getPeriodOfBookRent(bytes32 _key) external view returns (uint) {
        return store.get(periodsOfBookRent, _key);
    }

    function setPeriodOfBookRent(bytes32 _key, uint period) external returns (bool) {
        store.set(periodsOfBookRent, _key, period);
        return true;
    }


    function() public {
        revert();
    }

}
