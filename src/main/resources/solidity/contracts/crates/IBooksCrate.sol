pragma solidity 0.4.18;

contract IBooksCrate {

    enum BookStatus {
        RENTED,   // customer rent the book
        BUYED,    // customer buy the book
        RETURNED,   // customer return the book after rent
        EARLY_RETURN,   // customer return book because of some defect, etc.
        NEW_IN_SYSTEM    // the book was just added to the system
    }

    function addBook(bytes32 _key, bytes32 title, bytes32 author, bool isRentable, uint price, uint status) public returns (bool);

    function getBook(bytes32 _key) public view returns (bytes32[2], bool, uint[2], address);

    function getBookTitle(bytes32 _key) external view returns (bytes32);

    function getBookAuthor(bytes32 _key) external view returns (bytes32);

    function getBookIsRentable(bytes32 _key) external view returns (bool);

    function getBookPrice(bytes32 _key) external view returns (uint);

    function getBookStatus(bytes32 _key) external view returns (uint);

    function setBookStatus(bytes32 _key, BookStatus status) external returns (bool);

    function getBookOwner(bytes32 _key) external view returns (address);

    function setBookOwner(bytes32 _key, address newOwner) external returns (bool);

    function getPeriodOfBookRent(bytes32 _key) external view returns (uint);

    function setPeriodOfBookRent(bytes32 _key, uint period) external returns (bool);
}
