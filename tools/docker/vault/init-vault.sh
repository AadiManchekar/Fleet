#!/bin/sh
echo "-------------------------Starting Vault in Dev Mode-------------------------"

# Start Vault in the background (& is used to run the command in the background)
vault server -dev &

# Wait for Vault to start
sleep 10

echo "-------------------------Running init script-------------------------"

# -----------------------------
# VAULT ENGINE INITIALIZATION
# -----------------------------

# Enable Key-Value secrets engine v1
# This allows storing and versioning of secrets as simple key-value pairs
vault secrets enable -version=1 kv

# Enable Database secrets engine
# This will allow dynamic generation of database credentials (in future)
vault secrets enable database


# -----------------------------
# APPLICATION SECRET CONFIGURATION
# -----------------------------

# Configure secrets for dummy-child-module
vault kv put secret/dummy-child-module \
    # Sample database access credentials
    db.username=admin \
    db.password=password \
    # Sample application specific secrets
    secrettext=123456

echo "-------------------------Vault is ready-------------------------"

# Bring Vault to the foreground
# The wait command waits for the background process (vault server -dev) to finish.
# Since the Vault server runs indefinitely, the container will stay alive as long as the Vault server is running.
wait
