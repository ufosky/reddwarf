/*
 * This file provides declarations for unique identifiers.
 */

#ifndef SGS_ID_H
#define SGS_ID_H  1

#include "sgs/config.h"

typedef struct sgs_compact_id sgs_id;

/*
 * function: sgs_id_create()
 *
 * Creates an sgs_id from the specified byte array.
 */
sgs_id* sgs_id_create(const uint8_t* data, size_t len);

/*
 * function: sgs_id_destroy()
 *
 * Destroys an sgs_id, freeing any resources it was holding.
 */
void sgs_id_destroy(sgs_id* id);

/*
 * function: sgs_id_compare()
 *
 * Compares two IDs for equivalence.  Returns 0 if the IDs are equal, a value
 * less than 0 if a is less than b, or a value greater than 0 if a is
 * greater than b.
 */
int sgs_id_compare(const sgs_id* a, const sgs_id* b);

/*
 * function: sgs_id_equals_server()
 *
 * Returns a non-zero value if and only if the specified sgs_id matches the ID
 * of the server (canonically 0).
 */
int sgs_id_is_server(sgs_id* id);

/*
 * function: sgs_id_get_bytes()
 *
 * Returns the byte-array representation of the specified sgs_id.
 */
const uint8_t* sgs_id_get_bytes(const sgs_id* id);

/*
 * function: sgs_id_get_byte_len()
 *
 * Returns the number of bytes in the byte-array representation of the
 * specified sgs_id. If the sgs_id has not been initialized, it is "empty" and
 * this method will return 0;
 */
size_t sgs_id_get_byte_len(const sgs_id* id);

#endif /* !SGS_ID_H */
