package com.example.modoexamen.shared.utils
import org.junit.Assert.assertNotNull
import org.junit.Test

class DependenciesContainerTest {
    @Test
    fun `homeViewModelFactory should be created successfully`() {
        // Arrange
        val dependenciesContainer = DependenciesContainer()

        // Act
        val homeViewModelFactory = dependenciesContainer.homeViewModel

        // Assert
        assertNotNull(homeViewModelFactory)
    }

    @Test
    fun `feedViewModelFactory should be created successfully`() {
        // Arrange
        val dependenciesContainer = DependenciesContainer()

        // Act
        val feedViewModelFactory = dependenciesContainer.feedViewModel

        // Assert
        assertNotNull(feedViewModelFactory)
    }
}
