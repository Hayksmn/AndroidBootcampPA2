package com.self.admin.bootcamp

class Stack<T>(val maxSize: Int = 3) {

    var items: MutableList<T> = mutableListOf()

    fun isEmpty(): Boolean = items.isEmpty()

    fun size(): Int = items.count()

    fun push(element: T) {
        if (size() >= maxSize) {
            dequeue()
        }
        items.add(element)
    }

    fun pop() : T? {
        val item = items.lastOrNull()
        if (!isEmpty()){
            items.removeAt(items.size -1)
        }
        return item
    }

    fun dequeue(): T? {
        if (!this.isEmpty()) {
            return items.removeAt(0)
        }
        return null
    }

    fun peek(): T? = items.lastOrNull()

    fun clear(){
        items = mutableListOf()
    }

    override fun toString() = items.toString()
}