package fr.iut.uca.v1.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for repositories
 * @param <T> Type of the repository
 */
public interface IGenericRepository<T> {

    /**
     * Get the number of items in the repository
     * @return Number of items in the repository
     */
    public long getCount();

    /**
     * Get a list of items from the repository
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of items
     */
    public List<T> getItems(int index, int count);

    /**
     * Get an item by its id
     * @param id Id of the item
     * @return Item if found
     */
    public Optional<T> getItemById(String id);

    /**
     * Add an item to the repository
     * @param item Item to add
     * @return Added item if successful
     */
    public Optional<T> addItem(T item);

    /**
     * Update an item in the repository
     * @param item Item to update
     * @return Updated item if successful
     */
    public Optional<T> updateItem(T item);

    /**
     * Delete an item from the repository
     * @param id Id of the item to delete
     * @return True if successful, false otherwise
     */
    public boolean deleteItem(String id);
}
